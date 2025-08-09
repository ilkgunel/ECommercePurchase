package com.ecommercepurchase.test;

import com.ecommercepurchase.entities.SalesPerson;
import com.ecommercepurchase.exception.UnacceptableBillException;
import com.ecommercepurchase.record.BillRequest;
import com.ecommercepurchase.record.BillResponse;
import com.ecommercepurchase.repository.BillRepository;
import com.ecommercepurchase.repository.SalesPersonRepository;
import com.ecommercepurchase.service.BillService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.when;

@ExtendWith(SpringExtension.class)
//@TestPropertySource(properties = {"com.ecommercepurchase.limit=200"})
@TestPropertySource("classpath:/test.properties")
public class BillServiceTest {

    @Value("${com.ecommercepurchase.limit}")
    private int salesPersonLimit;

    @Mock
    private SalesPersonRepository salesPersonRepository;

    @Mock
    private BillRepository billRepository;

    @InjectMocks
    private BillService billService;

    @Test
    public void testAddBill() {

        ReflectionTestUtils.setField(billService, "salesPersonLimit", salesPersonLimit);

        SalesPerson salesPerson = new SalesPerson();
        salesPerson.setId(1L);
        salesPerson.setFirstName("Jane");
        salesPerson.setLastName("Doe");
        salesPerson.setEmail("jane@doe.com");

        when(salesPersonRepository.findByFirstNameAndLastNameAndEmail(any(), any(), any())).thenReturn(salesPerson);

        BillRequest billRequest = new BillRequest("Jane", "Doe", "jane@doe.com", 100L, "Macbook Pro", 1L);
        BillResponse billResponse = billService.addBill(billRequest);

        assertThat(billResponse.status()).isEqualTo("ONAYLANDI");
        assertThat(billResponse.resultMessage()).isEqualTo("Fatura Kabul Edildi");
    }

    @Test
    public void testAddBillUnacceptableBillException() {
        ReflectionTestUtils.setField(billService, "salesPersonLimit", salesPersonLimit);

        SalesPerson salesPerson = new SalesPerson();
        salesPerson.setId(1L);
        salesPerson.setFirstName("Jane");
        salesPerson.setLastName("Doe");
        salesPerson.setEmail("jane@doe.com");

        when(salesPersonRepository.findByFirstNameAndLastNameAndEmail(any(), any(), any())).thenReturn(salesPerson);

        BillRequest billRequest = new BillRequest("Jane", "Doe", "jane@doe.com", Long.valueOf(salesPersonLimit) + 1 , "Macbook Pro", 1L);

        assertThrows(UnacceptableBillException.class, () -> billService.addBill(billRequest));

    }
}
