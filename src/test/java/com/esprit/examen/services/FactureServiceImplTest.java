package com.esprit.examen.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Facture;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class FactureServiceImplTest {
    @Autowired
    IFactureService factureService;

    @Test
    public void testRetrieveAllFactures() throws ParseException {

        List<Facture> factures = factureService.retrieveAllFactures();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Facture facture = new Facture();
        Facture savedFacture = factureService.addFacture(facture);
        log.info(" Facture : " + facture);

        assertNotNull(savedFacture.getIdFacture());

        factureService.retrieveFacture(facture.getIdFacture());

        List<Facture> factureList = factureService.retrieveAllFactures();
        log.info("Les  Factures : " + factureList);
    }

    @Test
    public void testAddFacture() throws ParseException{
        List<Facture> factures = factureService.retrieveAllFactures();
        int size1 = factures.size();
        //SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //Date date1 = dateFormat.parse("10/02/2020");
        //Date date2 = dateFormat.parse("12/09/2022");
        Facture f = factureService.addFacture(new Facture());
        assertNotNull(f.getIdFacture());
        log.info("facture \n "+ f);
        log.info("size1 "+factures.size());
        factureService.cancelFacture(f.getIdFacture());
        factures = factureService.retrieveAllFactures();
        log.info("size1 "+factures.size());
        assertNotEquals(size1, factures.size());
    }
}