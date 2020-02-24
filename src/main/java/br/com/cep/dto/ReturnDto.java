package br.com.cep.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "vo")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReturnDto implements Serializable {

	private static final long serialVersionUID = 6598816941284549874L;
	private String idUser;
	private String messageReturn;
	private Integer cdReturn;

	/**
	 * @return the idUser
	 */
	public String getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	/**
	 * @return the messageReturn
	 */
	public String getMessageReturn() {
		return messageReturn;
	}

	/**
	 * @param messageReturn the messageReturn to set
	 */
	public void setMessageReturn(String messageReturn) {
		this.messageReturn = messageReturn;
	}

	/**
	 * @return the cdReturn
	 */
	public Integer getCdReturn() {
		return cdReturn;
	}

	/**
	 * @param cdReturn the cdReturn to set
	 */
	public void setCdReturn(Integer cdReturn) {
		this.cdReturn = cdReturn;
	}

}
