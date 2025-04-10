package de.steinuntersteinen.jerp.core;

import de.steinuntersteinen.jerp.core.Confirmation.Confirmation;
import de.steinuntersteinen.jerp.core.Invoice.Invoice;
import de.steinuntersteinen.jerp.core.Invoice.InvoiceBuilder;
import de.steinuntersteinen.jerp.core.Invoice.PDFInvoice;
import de.steinuntersteinen.jerp.core.Persistence.DataBase;
import de.steinuntersteinen.jerp.core.Persistence.User;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

public class AppFacade {
    private User user;
    private Invoice invoice;
    private PDDocument document;

    public AppFacade() {
        DataBase.init();
    }

    public void createUser() {
        user = new User();
    }
    
    public void saveUser() {
        try {
            DataBase.add(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void loadUser() throws Exception {
        user = DataBase.loadUser();
    }

    public void createInvoice() {
        createInvoice(null);
    }

    public void createInvoice(File file) {
        Confirmation confirmation = Confirmation.from(file);
        invoice = InvoiceBuilder.build(user, confirmation);
    }

    public File getPDFInvoice() throws IOException {
        PDFInvoice pdfInvoice = new PDFInvoice(invoice);
        document = pdfInvoice.getDocument();
        return File.createTempFile(invoice.getPath(), ".pdf");
    }

    public void saveInvoice(Invoice invoice) throws IOException {
        File file = getPDFInvoice();
        document.save(file);
    }

    public String getUserId() {
        return user.getId();
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public void setFirstName(String firstName) {
        user.setFirstName(firstName);
    }

    public String getLastName() {
        return user.getLastName();
    }

    public void setLastName(String lastName) {
         user.setLastName(lastName);
    }

    public String getProfession() {
        return user.getProfession();
    }

    public void setProfession(String profession) {
        user.setProfession(profession);
    }

    public String getPhoneNumber() {
        return user.getPhoneNumber();
    }

    public void setPhoneNumber(String phoneNumber) {
        user.setPhoneNumber(phoneNumber);
    }

    public String getEmail() {
        return user.getEmail();
    }

    public void setEmail(String email) {
        user.setEmail(email);
    }

    public String getWebsite() {
        return user.getWebsite();
    }

    public void setWebsite(String website) {
        user.setWebsite(website);
    }

    public String getStreet() {
        return user.getStreet();
    }

    public void setStreet(String street) {
        user.setStreet(street);
    }

    public String getCity() {
        return user.getCity();
    }

    public void setCity(String city) {
        user.setCity(city);
    }

    public String getZipCode() {
        return user.getZipCode();
    }

    public void setZipCode(String zipCode) {
        user.setZipCode(zipCode);
    }

    public String getBankName() {
        return user.getBankName();
    }

    public void setBankName(String bankName) {
        user.setBankName(bankName);
    }

    public String getIban() {
        return user.getIban();
    }

    public void setIban(String iban) {
        user.setIban(iban);
    }

    public String getBic() {
        return user.getBic();
    }

    public void setBic(String bic) {
        user.setBic(bic);
    }

    public String getTaxNumber() {
        return user.getTaxNumber();
    }

    public void setTaxNumber(String taxNumber) {
        user.setTaxNumber(taxNumber);
    }

    public String getPathToDocumentDirectory() {
        return user.getPathToDocumentDirectory();
    }

    public void setPathToDocumentDirectory(String pathToDocumentDirectory) {
        user.setPathToDocumentDirectory(pathToDocumentDirectory);
    }

    public int getInvoiceId() {
        return invoice.getId();
    }

    public void setId(int id) {
        invoice.setId(id);
    }

    public String getPath() {
        return invoice.getPath();
    }

    public void setPath(String path) {
        invoice.setPath(path);
    }

    public String getInvoiceNumber() {
        return invoice.getInvoiceNumber();
    }

    public void setInvoiceNumber(String invoiceNumber) {
        invoice.setInvoiceNumber(invoiceNumber);
    }

    public String getReturnAddress() {
        return invoice.getReturnAddress();
    }

    public void setReturnAddress(String returnAddress) {
        invoice.setReturnAddress(returnAddress);
    }

    public String getInvoiceAddress() {
        return invoice.getInvoiceAddress();
    }

    public void setInvoiceAddress(String invoiceAddress) {
        invoice.setInvoiceAddress(invoiceAddress);
    }

    public String getInvoiceDate() {
        return invoice.getInvoiceDate();
    }

    public void setInvoiceDate(String invoiceDate) {
        invoice.setInvoiceDate(invoiceDate);
    }

    public String getLetterText() {
        return invoice.getLetterText();
    }

    public void setLetterText(String letterText) {
        invoice.setLetterText(letterText);
    }

    public String getInterpretationLanguage() {
        return invoice.getInterpretationLanguage();
    }

    public void setInterpretationLanguage(String interpretationLanguage) {
        invoice.setInterpretationLanguage(interpretationLanguage);
    }

    public String getContractor() {
        return invoice.getContractor();
    }

    public void setContractor(String contractor) {
        invoice.setContractor(contractor);
    }

    public String getCustomer() {
        return invoice.getCustomer();
    }

    public void setCustomer(String customer) {
        invoice.setCustomer(customer);
    }

    public String getDeploymentDate() {
        return invoice.getDeploymentDate();
    }

    public void setDeploymentDate(String deploymentDate) {
        invoice.setDeploymentDate(deploymentDate);
    }

    public String getDuration() {
        return invoice.getDuration();
    }

    public void setDuration(String duration) {
        invoice.setDuration(duration);
    }

    public String getRate() {
        return invoice.getRate();
    }

    public void setRate(String rate) {
        invoice.setRate(rate);
    }

    public String getSum1() {
        return invoice.getSum1();
    }

    public void setSum1(String sum1) {
        invoice.setSum1(sum1);
    }

    public boolean isTravelPaid() {
        return invoice.isTravelPaid();
    }

    public void setTravelPaid(boolean travelPaid) {
        invoice.setTravelPaid(travelPaid);
    }

    public String getTravelFee() {
        return invoice.getTravelFee();
    }

    public void setTravelFee(String travelFee) {
        invoice.setTravelFee(travelFee);
    }

    public String getSumTotal() {
        return invoice.getSumTotal();
    }
    //TODO implement logic, that updates Sum total in setter of related values
    public void setSumTotal(String sumTotal) {
        invoice.setSumTotal(sumTotal);
    }

    public String getPaymentRequest() {
        return invoice.getPaymentRequest();
    }

    public void setPaymentRequest(String paymentRequest) {
        invoice.setPaymentRequest(paymentRequest);
    }

    public String getGreeting() {
        return invoice.getGreeting();
    }

    public void setGreeting(String greeting) {
        invoice.setGreeting(greeting);
    }

    public String getSignature() {
        return invoice.getSignature();
    }

    public void setSignature(String signature) {
        invoice.setSignature(signature);
    }
}