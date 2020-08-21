package br.ucamcampos.coolgrade.dto;

import java.io.Serializable;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.ucamcampos.coolgrade.services.validation.DisciplineInsert;

@DisciplineInsert
public class DisciplineNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 3, max = 120, message = "O tamanho deve ser entre 3 e 120 caracteres")
	private String name;

	@DecimalMax("10.00")
	@DecimalMin("00.00")
	private double grade1;

	@DecimalMax("10.00")
	@DecimalMin("00.00")
	private double grade2;

	@DecimalMax("10.00")
	@DecimalMin("00.00")
	private double grade3;

	@DecimalMax("10.00")
	@DecimalMin("00.00")
	private double gradePcl;

	private Integer student_id;

	public DisciplineNewDTO() {
	}

	public DisciplineNewDTO(String name, double grade1, double grade2, double grade3, double gradePcl,
			Integer student_id) {
		super();
		this.name = name;
		this.grade1 = grade1;
		this.grade2 = grade2;
		this.grade3 = grade3;
		this.gradePcl = gradePcl;
		this.student_id = student_id;
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

	public Integer getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}
	
}
