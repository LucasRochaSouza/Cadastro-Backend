package com.lucas;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.persistence.*;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	// Atributos do cadastro
	private int id;
	private String nome;
	private Date nascimento;
	private String fotoPath;
		
	// A foto não é salva no banco de dados e sim o seu caminho (path)
	public void setFoto(BufferedImage foto) {
		String path = nome + ".jpg";
		try {
			ImageIO.write(foto, "jpg", new File (path));
		} catch (IOException e) {
			System.out.print("Falha ao salvar o arquivo de foto");
			e.printStackTrace();
		}
		this.fotoPath = path;
	}
	
	// Setters e Getters
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
		
	public int getId() {
		return this.id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public Date getNascimento() {
		return this.nascimento;
	}
	
	public String getFotoPath() {
		return fotoPath;
	}
}