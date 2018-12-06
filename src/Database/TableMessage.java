/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wasim
 */
@Entity
@SequenceGenerator(name
        = "SequenceIdGeneratorMessage", sequenceName
        = "MESSGE_ID_SEQUENCE", allocationSize = 1)
@Table(name = "TABLE_MESSAGE", catalog = "", schema = "HR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TableMessage.findAll", query = "SELECT t FROM TableMessage t")
    , @NamedQuery(name = "TableMessage.findByIdMessage", query = "SELECT t FROM TableMessage t WHERE t.idMessage = :idMessage")})
public class TableMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
     @GeneratedValue(generator = "SequenceIdGeneratorMessage")  
    @Basic(optional = false)
    @Column(name = "ID_MESSAGE", nullable = false, precision = 22)
    private BigDecimal idMessage;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false)
    private String message;
    @JoinColumn(name = "FROM_USER_ID", referencedColumnName = "USERNAME_ID", nullable = false)
    @ManyToOne(optional = false)
    private TableUsername fromUserId;
    @JoinColumn(name = "TO_USER_ID", referencedColumnName = "USERNAME_ID", nullable = false)
    @ManyToOne(optional = false)
    private TableUsername toUserId;

    public TableMessage() {
    }

    public TableMessage(BigDecimal idMessage) {
        this.idMessage = idMessage;
    }

    public TableMessage(BigDecimal idMessage, String message) {
        this.idMessage = idMessage;
        this.message = message;
    }

    public BigDecimal getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(BigDecimal idMessage) {
        this.idMessage = idMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TableUsername getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(TableUsername fromUserId) {
        this.fromUserId = fromUserId;
    }

    public TableUsername getToUserId() {
        return toUserId;
    }

    public void setToUserId(TableUsername toUserId) {
        this.toUserId = toUserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMessage != null ? idMessage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TableMessage)) {
            return false;
        }
        TableMessage other = (TableMessage) object;
        if ((this.idMessage == null && other.idMessage != null) || (this.idMessage != null && !this.idMessage.equals(other.idMessage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.TableMessage[ idMessage=" + idMessage + " ]";
    }
    
}
