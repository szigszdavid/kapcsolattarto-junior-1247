package hu.futureofmedia.task.contactsapi.repositories;

import hu.futureofmedia.task.contactsapi.entities.Company;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyTest;

    @AfterEach
    void tearDown()
    {
        companyTest.deleteAll();
    }

    @Test
    void findAllTest() {
        List<Company> companyList = companyTest.findAll();

        assertThat(companyList.size()).isEqualTo(0);
    }

    @Test
    void findAllTestAfterAdd()
    {
        Company company = new Company(new Random().nextLong(),"Company #1");
        Company company2 = new Company(new Random().nextLong(),"Company #2");
        Company company3 = new Company(new Random().nextLong(),"Company #3");
        companyTest.save(company);
        companyTest.save(company2);
        companyTest.save(company3);


        List<Company> companyList = companyTest.findAll();

        assertThat(companyList.size()).isEqualTo(3);
    }

    @Test
    void findCompanyByNameTest() {

        Company company = new Company(new Random().nextLong(),"Company #1");
        Company company2 = new Company(new Random().nextLong(),"Company #2");
        Company company3 = new Company(new Random().nextLong(),"Company #3");
        companyTest.save(company);
        companyTest.save(company2);
        companyTest.save(company3);

        Company companyFind  = companyTest.findCompanyByName("Company #1");
        assertThat(companyFind.getId()).isEqualTo(company.getId());
        assertThat(companyFind.getName()).isEqualTo(company.getName());
    }
}