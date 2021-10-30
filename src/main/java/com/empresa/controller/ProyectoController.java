package com.empresa.controller;

import java.io.File;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;


@RestController
public class ProyectoController {

	/*@GetMapping("/saludo")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="valor por defecto") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}*/
	

	
	@GetMapping("/OCR")
	public String OCR(@RequestParam Map<String,String> requestParams , Model model)  {
		
		String name=requestParams.get("ruta");
		String español=requestParams.get("spa");
		String ingles=requestParams.get("eng");
		String frances=requestParams.get("fra");
		String aleman=requestParams.get("deu");
		String portugues=requestParams.get("por");
		
		ITesseract image = new Tesseract();
		
		image.setDatapath("D:\\Program Files\\Tesseract-OCR\\tessdata");

		if(español!=null) {
			image.setLanguage("spa");
		}
		else if(ingles!=null) {
			image.setLanguage("eng");
		}
		else if(frances!=null) {
			image.setLanguage("fra");
		}
		else if(portugues!=null) {
			image.setLanguage("por");
		}
		else if(aleman!=null) {
		image.setLanguage("deu");
		} 
		
//		image.setLanguage("eng+spa+por+deu+fra");

		try {
			String str = image.doOCR(new File("F:\\Descargas\\Proy. Integrador\\"+name));
			
			model.addAttribute("OCRtext", str);
			
		} catch (TesseractException e) {
			System.out.println("Exception " + e.getMessage());
		}
		return "ocr";
	} 

	
	
	
}
