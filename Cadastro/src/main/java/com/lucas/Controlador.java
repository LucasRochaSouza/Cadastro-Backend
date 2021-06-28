package com.lucas;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// Controlador, define as operacoes HTTPS de POST e GET e seus parâmetros 
@Controller 
@RequestMapping(path="/cadastro") 
public class Controlador {
	@Autowired
	private Repositorio userRepository;
  
	// POST com parametros de nome, nascimento e path 
	@PostMapping(path="/adicionar") 
	public @ResponseBody String novoUsuario (@RequestParam String nome, @RequestParam String nascimento,
		  @RequestParam String path) {
	  
	Date data;
	BufferedImage foto;
	Usuario usuario = new Usuario();
	
	try {
		data = new SimpleDateFormat("dd/MM/yyyy").parse(nascimento);
		foto = ImageIO.read(new File(path));
	} catch (ParseException e) {
		// Caso a data esteja em um formato errado retorna um erro
		return "Erro ao digitar a data";
	}  catch (IOException e) {
		// Caso tenha alguma problema ao ler o arquivo retorna um erro
		return "Erro ao ler a imagem";
	}
	// Com os dados prontos usa os setters com os parâmetros 
    usuario.setFoto(foto);
    usuario.setNome(nome);
    usuario.setNascimento(data);
    
    userRepository.save(usuario);
    return "Sucesso";
  }
  // GET para ler todos os usuarios do banco de dados
  @GetMapping(path="/ler")
  public @ResponseBody Iterable<Usuario> listarTodosUsuarios() {
	  return userRepository.findAll();
  }
}
