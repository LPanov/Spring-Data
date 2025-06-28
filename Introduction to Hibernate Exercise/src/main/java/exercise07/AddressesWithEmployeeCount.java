package exercise07;

import dtos.AddressSummaryDto;
import entities.Address;
import helper.EntityManagerHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AddressesWithEmployeeCount {
    public static void main(String[] args) {
        EntityManager em = EntityManagerHelper.createEntityManager();

        TypedQuery<AddressSummaryDto> query = em.createQuery("select a.text, a.town.name, size(a.employees) from Address a left join a.town order by size(a.employees) desc", AddressSummaryDto.class)
                .setMaxResults(10);
        List<AddressSummaryDto> addresses = query.getResultList();

        for (AddressSummaryDto address : addresses) {
            String townName = address.getTown() == null ? "" : address.getTown();
            System.out.printf("%s, %s - %d employees%n", address.getText(), townName, address.getEmployees());
        }


    }
}
