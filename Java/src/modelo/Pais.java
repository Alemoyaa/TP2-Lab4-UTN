/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
 
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pais")
public class Pais implements Serializable {
     
    @Id
    @Column(name="codigoPais")
    private int _codigoPais;
    
    @Column(name="nombrePais")
    private String _nombrePais;
    
    @Column(name="capitalPais")
    private String _capitalPais;
    
    @Column(name="region")
    private String _region;
    
    @Column(name="poblacion")
    private long _poblacion;
    
    @Column(name="latitud")
    private float _latitud;
    
    @Column(name="longitud")
    private float _longitud;

    public Pais() {
    }
    
    public Pais(int _codigoPais, String _nombrePais, String _capitalPais, String _region, long _poblacion, float _latitud, float _longitud) {
        this._codigoPais = _codigoPais;
        this._nombrePais = _nombrePais;
        this._capitalPais = _capitalPais;
        this._region = _region;
        this._poblacion = _poblacion;
        this._latitud = _latitud;
        this._longitud = _longitud;
    }

    public int getCodigoPais() {
        return _codigoPais;
    }

    public void setCodigoPais(int _codigoPais) {
        this._codigoPais = _codigoPais;
    }

    public String getNombrePais() {
        return _nombrePais;
    }

    public void setNombrePais(String _nombrePais) {
        this._nombrePais = _nombrePais;
    }

    public String getCapitalPais() {
        return _capitalPais;
    }

    public void setCapitalPais(String _capitalPais) {
        this._capitalPais = _capitalPais;
    }

    public String getRegion() {
        return _region;
    }

    public void setRegion(String _region) {
        this._region = _region;
    }

    public long getPoblacion() {
        return _poblacion;
    }

    public void setPoblacion(long _poblacion) {
        this._poblacion = _poblacion;
    }

    public float getLatitud() {
        return _latitud;
    }

    public void setLatitud(float _latitud) {
        this._latitud = _latitud;
    }

    public float getLongitud() {
        return _longitud;
    }

    public void setLongitud(float _longitud) {
        this._longitud = _longitud;
    }

    @Override
    public String toString() {
        return "pais{" + "_codigoPais=" + _codigoPais + ", _nombrePais=" + _nombrePais + ", _capitalPais=" + _capitalPais + ", _region=" + _region + ", _poblacion=" + _poblacion + ", _latitud=" + _latitud + ", _longitud=" + _longitud + '}';
    }

    public int get_codigoPais() {
        return _codigoPais;
    }

    public void set_codigoPais(int _codigoPais) {
        this._codigoPais = _codigoPais;
    }

}
