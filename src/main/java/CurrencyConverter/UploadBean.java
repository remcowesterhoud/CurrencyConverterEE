package CurrencyConverter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Remco on 26-10-2015.
 */
@ManagedBean(name = "uploadBean")
@SessionScoped
public class UploadBean {

    private Part file;
    private FileParser parser;

    public UploadBean(){
        file = null;
        parser = new FileParser();
    }

    public String read(Part file){
        try{
            String fileInString = null;
            if (file != null){
                Scanner scanner = new Scanner(file.getInputStream(), "UTF-8").useDelimiter("\\A");
                fileInString = scanner.hasNext() ? scanner.next() : "";
            }
            return fileInString;
        }
        catch (IOException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload failed!"));
            return null;
        }
    }

    public void uploadOverride(){
        String fileString = read(file);
        parser.parseStringOverride(fileString);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload succesfully ended!"));
    }

    public void uploadAdd(){
        String fileString = read(file);
        parser.parseStringAdd(fileString);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload succesfully ended!"));
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
}
