package com.empresa.controller;

import java.io.File;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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


	
	@RequestMapping(value="/OCR", method =  RequestMethod.GET)
	public ModelAndView OCR(@RequestParam Map<String,String> requestParams, Model model) {

	//@RequestParam("ruta") MultipartFile multipartFile ,Model model

		
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("ocr");
	    
		String name=requestParams.get("ruta");
		String español=requestParams.get("spa");
		String ingles=requestParams.get("eng");
		String frances=requestParams.get("fra");
		String aleman=requestParams.get("deu");
		String portugues=requestParams.get("por");
		
		//String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        
		
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
			//URLbase agregado proximamente
			
			model.addAttribute("OCRtext", str);
			//System.out.println(filename);
			
		} catch (TesseractException e) {
			System.out.println("Exception " + e.getMessage());
		}
		return modelAndView;
	} 

	
	
	
}
