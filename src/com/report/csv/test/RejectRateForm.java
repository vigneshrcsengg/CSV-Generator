package com.report.csv.test;

/**
 *
 * @author VIGNESH R
 */
public class RejectRateForm
{

    private String termid;
    private String trxdate;
    private String trxtime;
    private String encashed;
    private String refunded;
    private int escrow;
    private int rejected;
    private float rejectrate;
    private String acceptedNotes;
    private String rejectedNotes;

    public String gettermid()
    {
        return termid;
    }
    
    public void settermid(String termid)
    {
        this.termid = termid;
    }

    public String gettrxdate()
    {
        return trxdate;
    }

    public void settrxdate(String trxdate)
    {
        this.trxdate = trxdate;
    }

    public String gettrxtime()
    {
        return trxtime;
    }

    public void settrxtime(String trxtime)
    {
        this.trxtime = trxtime;
    }

    public String getencashed()
    {
        return encashed;
    }

    public void setencashed(String encashed)
    {
        this.encashed = encashed;
    }

    public String getrefunded()
    {
        return refunded;
    }

    public int getescrow()
    {
        return escrow;
    }

    public void setescrow(int escrow)
    {
        this.escrow = escrow;
    }

    public int getrejected()
    {
        return rejected;
    }

    public float getrejectrate()
    {
        return rejectrate;
    }

    public void setrejectrate(float rejectrate)
    {
        this.rejectrate = rejectrate;
    }
    
    public void setrefunded(String refunded)
    {
        this.refunded = refunded;
    }

    public String getacceptedNotes()
    {
        return acceptedNotes;
    }

    public void setacceptedNotes(String acceptedNotes)
    {
        this.acceptedNotes = acceptedNotes;
    }

    public String getrejectedNotes()
    {
        return rejectedNotes;
    }

    public void setrejectedNotes(String rejectedNotes)
    {
        this.rejectedNotes = rejectedNotes;
    }
    
    public void setrejected(int rejected)
    {
        this.rejected = rejected;
    }

    public void serefunded(String refunded)
    {
        this.refunded = refunded;
    }

    public void serejected(int rejected)
    {
        this.rejected = rejected;
    }
}
