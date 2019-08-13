package main;

import static spark.Spark.*;

import java.util.*;

import com.google.gson.Gson;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Launcher {

	public static void main(String[] args) {
		staticFiles.location("/public");
		String path="domovi.json";
		
		HashMap<String,Object> polja=new HashMap<>();
		
		get("/",(request,response)->{
			polja.put("podaci",Data.readFromJson(path));			
			return new ModelAndView(polja,"index.hbs");
		},new HandlebarsTemplateEngine());
		
		
		get("/dodavanje",(request,response)->{
			ArrayList<String> lis= new ArrayList<>();
			for(Dom d:Data.readFromJson(path))
			{
				if(!lis.contains(d.getImeDoma()))
				{
					lis.add(d.getImeDoma());
				}
			}
			polja.put("domovi",lis);			
			return new ModelAndView(polja,"dodavanje.hbs");
		},new HandlebarsTemplateEngine());
		
		
		
		get("/izmena",(request,response)->{
			ArrayList<String> lis= new ArrayList<>();
			for(Dom d:Data.readFromJson(path))
			{
				if(!lis.contains(d.getImeDoma()))
				{
					lis.add(d.getImeDoma());
				}
			}
			polja.put("domovi",lis);			
			return new ModelAndView(polja,"izmena.hbs");
		},new HandlebarsTemplateEngine());
		
		
		
		
		post("/brojsoba",(request,response)->{
			response.type("text/text");
			int broj1 = Integer.parseInt(request.queryParams("broj1"));
			int broj2 = Integer.parseInt(request.queryParams("broj2"));
			
			ArrayList<Dom> tmp = new ArrayList<>();
			for(Dom x:Data.readFromJson(path)) {
				if(x.getBrojSoba()>= broj1 && x.getBrojSoba()<broj2)
				{
					tmp.add(x);
				}
			}
			
			
			Gson gson=new Gson();
			return gson.toJson(tmp);
		});
		
		post("/domovi",(request,response)->{
			response.type("text/text");
			
			String dom =request.queryParams("dom");
			String tip=request.queryParams("tip");
			int cena=Integer.parseInt(request.queryParams("cena"));
			boolean kupatiloUSobi=Boolean.parseBoolean(request.queryParams("kupatiloUSobi"));			
			
			ArrayList<Dom> listica = Data.readFromJson(path);
			Soba s = new Soba(tip,cena,kupatiloUSobi);
			
			
		    for(Dom d:listica) {
		    	 if(d.getImeDoma().equals(dom)){
		    		 d.getTipSobe().add(s);
		    	 }
		    }
		    
		    Data.writeToJSON(listica, path);
		    return true;
			
			
		});
		
		 post("/izmeniPodatke",(request,respone)->{
	        	respone.type("text/text");
	        	String dom=request.queryParams("dom");
	        	ArrayList<Dom> list=Data.readFromJson(path);
	        	
	        	for(Dom d:list) {
	        		if(d.equals(dom)) {
	        			Gson gson=new Gson();
	    	        	return gson.toJson(d);
	        		}
	        	}
	        	Gson gson=new Gson();
	        	return gson.toJson(dom);
	        });
		 
		 post("/prikaziSobe",(request,response)->{
				response.type("text/text");
				String trazenidom=request.queryParams("dom");
				ArrayList<String> tmp = new ArrayList<>();
				for(Dom d:Data.readFromJson(path)) {
					if(d.getImeDoma().equals(trazenidom)) {
						for(Soba s:d.getTipSobe()) {
							tmp.add(s.getTip());
						}
					}
				}
				
				
				Gson gson=new Gson();
				return gson.toJson(tmp);
			});
		 
		 post("/prikaziKupatila",(request,response)->{
				response.type("text/text");
			
				String trazenidom=request.queryParams("dom");
				String trazenaSoba=request.queryParams("soba");
				ArrayList<Boolean> tmp = new ArrayList<>();
				for(Dom d:Data.readFromJson(path)) {
					if(d.getImeDoma().equals(trazenidom)) {
						for(Soba s:d.getTipSobe()) {
							if(s.getTip().equals(trazenaSoba)) {
								tmp.add(s.isKupatiloUSobi());
							}
						}
					}
				}
				
				
				Gson gson=new Gson();
				return gson.toJson(tmp);
			});
		
		 post("/izmeniCenu",(request,response)->{
				response.type("text/text");
				
				String dom =request.queryParams("dom");
				String soba=request.queryParams("soba");				
				boolean kupatilo=Boolean.parseBoolean(request.queryParams("kupatilo"));		
				int cenaa=Integer.parseInt(request.queryParams("cena"));
				
				ArrayList<Dom> listica = Data.readFromJson(path);
					
				
			    for(Dom d:listica) {
			    	 if(d.getImeDoma().equals(dom)){
			    		 for(Soba x:d.getTipSobe())
			    		 {
			    			 if(x.getTip().equals(soba) && (x.isKupatiloUSobi()==kupatilo))
			    			 {
			    				 x.setCena(cenaa);
			    			 }
			    		 }
			    	 }
			    }
			    
			    Data.writeToJSON(listica, path);
			    return true;
				
				
			});
		

}
}
