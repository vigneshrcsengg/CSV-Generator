package com.report.csv.test;

/**
 *
 * @author Vignesh R
 */
public class Employe
{

    private int empId;
    private String empDOB;
    private String empName;
    private String empDesg;
    private double empSalary;

    /**
     *
     * @param empId
     * @param empName
     * @param empDOB
     * @param empDesg
     * @param empSalary
     */
    public Employe(int empId, String empName, String empDOB, String empDesg, double empSalary)
    {
        super();
        this.empId = empId;
        this.empDOB = empDOB;
        this.empName = empName;
        this.empDesg = empDesg;
        this.empSalary = empSalary;
    }

    // <editor-fold defaultstate="collapsed" desc="Get & Set Methods">
    /**
     *
     * @return
     */
    public int getempId()
    {
        return empId;
    }

    /**
     *
     * @param empId
     */
    public void setempId(int empId)
    {
        this.empId = empId;
    }

    /**
     *
     * @return
     */
    public String getempDOB()
    {
        return empDOB;
    }

    /**
     *
     * @param empDOB
     */
    public void setempDOB(String empDOB)
    {
        this.empDOB = empDOB;
    }

    /**
     *
     * @return
     */
    public String getempName()
    {
        return empName;
    }

    /**
     *
     * @param empName
     */
    public void setempName(String empName)
    {
        this.empName = empName;
    }

    /**
     *
     * @return
     */
    public String getempDesg()
    {
        return empDesg;
    }

    /**
     *
     * @param empDesg
     */
    public void setempDesg(String empDesg)
    {
        this.empDesg = empDesg;
    }

    /**
     *
     * @return
     */
    public double getempSalary()
    {
        return empSalary;
    }

    /**
     *
     * @param empSalary
     */
    public void setempSalary(double empSalary)
    {
        this.empSalary = empSalary;
    }
    // </editor-fold>
}
