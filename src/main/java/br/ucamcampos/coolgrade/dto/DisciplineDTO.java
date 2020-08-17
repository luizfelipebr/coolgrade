package br.ucamcampos.coolgrade.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import br.ucamcampos.coolgrade.domain.Discipline;
import br.ucamcampos.coolgrade.services.validation.StudentUpdate;

import javax.validation.constraints.NotEmpty;

@StudentUpdate
public class DisciplineDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 3, max = 120, message = "O tamanho deve ser entre 3 e 120 caracteres")
	private String name;

	private double grade1;
	private double grade2;
	private double grade3;
	private double gradePcl;
	
	

	public DisciplineDTO() {
	}

	public DisciplineDTO(Discipline obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.grade1 = obj.getGrade1();
		this.grade2 = obj.getGrade2();
		this.grade3 = obj.getGrade3();
		this.gradePcl = obj.getGradePcl();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getGrade1() {
		return grade1;
	}

	public void setGrade1(double grade1) {
		this.grade1 = grade1;
	}

	public double getGrade2() {
		return grade2;
	}

	public void setGrade2(double grade2) {
		this.grade2 = grade2;
	}

	public double getGrade3() {
		return grade3;
	}

	public void setGrade3(double grade3) {
		this.grade3 = grade3;
	}

	public double getGradePcl() {
		return gradePcl;
	}

	public void setGradePcl(double gradePcl) {
		this.gradePcl = gradePcl;
	}

}
