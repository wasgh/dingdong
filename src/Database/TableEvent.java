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
import javax.persistence.Lob;
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
        = "SequenceIdGeneratorEvent", sequenceName
        = "EVENT_ID_SEQUENCE", allocationSize = 1)@Table(name = "TABLE_EVENT", catalog = "", schema = "HR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TableEvent.findAll", query = "SELECT t FROM TableEvent t")
    , @NamedQuery(name = "TableEvent.findByIdEvent", query = "SELECT t FROM TableEvent t WHERE t.idEvent = :idEvent")})
public class TableEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Id
    @GeneratedValue(generator = "SequenceIdGeneratorEvent")    @Basic(optional = false)
    @Column(name = "ID_EVENT", nullable = false, precision = 22)
    private BigDecimal idEvent;
    @Lob
    private String text;

    public TableEvent() {
    }

    public TableEvent(BigDecimal idEvent) {
        this.idEvent = idEvent;
    }

    public BigDecimal getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(BigDecimal idEvent) {
        this.idEvent = idEvent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvent != null ? idEvent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TableEvent)) {
            return false;
        }
        TableEvent other = (TableEvent) object;
        if ((this.idEvent == null && other.idEvent != null) || (this.idEvent != null && !this.idEvent.equals(other.idEvent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.TableEvent[ idEvent=" + idEvent + " ]";
    }
    
}
