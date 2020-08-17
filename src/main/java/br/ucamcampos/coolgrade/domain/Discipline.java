package br.ucamcampos.coolgrade.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Discipline implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private double grade1;
	private double grade2;
	private double grade3;
	private double gradePcl;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Student student;

	public Discipline() {
	}

	public Discipline(Integer id, String name, double grade1, double grade2, double grade3, double gradePcl,
			Student student) {
		super();
		this.id = id;
		this.name = name;
		this.grade1 = grade1;
		this.grade2 = grade2;
		this.grade3 = grade3;
		this.gradePcl = gradePcl;
		this.student = student;
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

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
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

	public double getAverageGradeMax() {
		double maxAvg,value1,value2,value3 = 0.0;
		value1 = (this.grade1 + this.gradePcl + this.grade2)/2;
		value2 = (this.grade1 + this.gradePcl + this.grade3)/2;
		value3 = (this.grade2 + this.grade3)/2;
		
		if (value1 > value2) {
			if(value1 > value3) {
				maxAvg = value1;
			}
			else {
				maxAvg = value3;
			}
		}
		else if(value2 > value3) {
			maxAvg = value2;
		}
		else {
			maxAvg = value3;
		}
		
		return maxAvg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Discipline other = (Discipline) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
