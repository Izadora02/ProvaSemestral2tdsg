package br.com.fiap.bean;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.primefaces.model.file.UploadedFile;

import br.com.fiap.dao.VisitanteDao;
import br.com.fiap.model.Visitante;


@Named
@RequestScoped
public class VisitanteBean {
	
	private Visitante visi = new Visitante();
	
	private UploadedFile image;
	
	@Inject
	private VisitanteDao dao;
	
	public String save() throws IOException  {
		System.out.println(this.visi);
		
		ServletContext servletContext = (ServletContext) FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getContext();
		String path = servletContext.getRealPath("/");
		
		FileOutputStream out = 
				new FileOutputStream(path + "\\images\\" + image.getFileName());
		out.write(image.getContent());
		out.close();
		
		visi.setImagePath("\\images\\" + image.getFileName());
		
		dao.create(visi);
		
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("Setup cadastrado com sucesso"));
		
		return "visi";
	}
	
	
	public List<Visitante> getAll(){
		return dao.listAll();
	}
	
	public Visitante getVisi() {
		return visi;
	}

	public void setVisi(Visitante visi) {
		this.visi = visi;
	}
	
	public UploadedFile getImage() {
		return image;
	}

	public void setImage(UploadedFile image) {
		this.image = image;
	}

}
