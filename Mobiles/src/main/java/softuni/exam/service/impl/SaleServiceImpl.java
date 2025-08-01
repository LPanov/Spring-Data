package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.SaleInputDto;
import softuni.exam.models.entity.Sale;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.SaleRepository;
import softuni.exam.service.SaleService;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class SaleServiceImpl implements SaleService {

    private final ModelMapper modelMapper;
    private final ValidationUtil validator;
    private final Gson gson;
    private final SaleRepository saleRepository;
    private final SellerService sellerService;


    public SaleServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, SaleRepository saleRepository, SellerService sellerService) {
        this.modelMapper = modelMapper;
        this.validator = validationUtil;
        this.gson = gson;
        this.saleRepository = saleRepository;
        this.sellerService = sellerService;
    }

    @Override
    public boolean areImported() {
        return saleRepository.count() > 0;
    }

    @Override
    public String readSalesFileContent() throws IOException {
        Path path = Path.of("src/main/resources/files/json/sales.json");
        return Files.readString(path);
    }

    @Override
    public String importSales() throws IOException {
        SaleInputDto[] inputDtos = gson.fromJson(readSalesFileContent(), SaleInputDto[].class);

        StringBuilder sb = new StringBuilder();
        for (SaleInputDto inputDto : inputDtos) {
            Sale createdSale = create(inputDto);

            if (createdSale == null) sb.append(String.format("Invalid sale%n"));
            else sb.append(String.format("Successfully imported sale with number %s%n", createdSale.getNumber()));
        }

        return sb.toString();
    }

    private Sale create(SaleInputDto inputDto) {
        if (!validator.isValid(inputDto)) return null;

        try {
            Sale sale = modelMapper.map(inputDto, Sale.class);
            sale.setSaleDate(LocalDateTime.parse(inputDto.getSaleDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            Long sellerId = inputDto.getSeller();
            if (sellerId != null) {
                Seller seller = sellerService.getReferenceById(sellerId);
                sale.setSeller(seller);
            }

            saleRepository.save(sale);

            return sale;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Sale getReferencedById(Long id) {
        return saleRepository.getReferenceById(id);
    }
}
