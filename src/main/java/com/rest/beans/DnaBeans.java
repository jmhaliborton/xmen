package com.rest.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


//@XmlRootElement
public class DnaBeans 
{
//	@XmlElement(name = "dna")
	private List<String> dna;
	private boolean mutante = false;

	
	public List<String> getDna()
	{
		return dna;
	}

	
	public void setDna(List<String> dnaL)
	{
		this.dna = dnaL;
	}


	
	public boolean isMutante()
	{
		return mutante;
	}


	
	public void setMutante(boolean mutante)
	{
		this.mutante = mutante;
	}
	
	
}
