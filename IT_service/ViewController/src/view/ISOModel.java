package view;

public class ISOModel {
    public ISOModel() {
        super();
    }
    
    public String Branch  ;

       public String System  ;

       public String Medium  ;
    
    public String Person  ;
    public String Intimated_Date  ; 
    public String Problem  ; 
    public String Resolved_On  ;
    public String Status  ;


    public void setBranch(String Branch) {
        this.Branch = Branch;
    }

    public String getBranch() {
        return Branch;
    }

    public void setSystem(String System) {
        this.System = System;
    }

    public String getSystem() {
        return System;
    }

    public void setMedium(String Medium) {
        this.Medium = Medium;
    }

    public String getMedium() {
        return Medium;
    }

    

    public void setPerson(String Person) {
        this.Person = Person;
    }

    public String getPerson() {
        return Person;
    }

    public void setIntimated_Date(String Intimated_Date) {
        this.Intimated_Date = Intimated_Date;
    }

    public String getIntimated_Date() {
        return Intimated_Date;
    }

    public void setProblem(String Problem) {
        this.Problem = Problem;
    }

    public String getProblem() {
        return Problem;
    }

    public void setResolved_On(String Resolved_On) {
        this.Resolved_On = Resolved_On;
    }

    public String getResolved_On() {
        return Resolved_On;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getStatus() {
        return Status;
    }
}
