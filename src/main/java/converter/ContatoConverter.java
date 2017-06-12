package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import entity.Contato;
import persistence.ContatoDao;

@FacesConverter(forClass = Contato.class)
public class ContatoConverter implements Converter {

	private ContatoDao contatoDao = new ContatoDao();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		// Fabricante retorno = null;
		//
		// if (value != null) {
		// retorno = this.fabricanteDAO.buscarPeloCodigo(new Long(value));
		// }
		//
		// return retorno;

		Contato retorno = null;
		if (value != null) {
			retorno = this.contatoDao.findByCode(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
//		if (value != null) {
//			Long codigo = ((Fabricante) value).getCodigo();
//			String retorno = (codigo == null ? null : codigo.toString());
//			
//			return retorno;
//		}
//		
//		return "";
		
		if (value != null){
			Long codigo = ((Contato)value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}
}
