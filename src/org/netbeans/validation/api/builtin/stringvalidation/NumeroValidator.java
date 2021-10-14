package org.netbeans.validation.api.builtin.stringvalidation;

import org.netbeans.validation.api.Problems;
import org.openide.util.NbBundle;

/**
 * Clase para validar la edad
 * @author Lourdes Navarro Tocón
 */
public class NumeroValidator extends StringValidator {

    /**
     * Método que permite validar la edad
     * @param problems Problemas de validación
     * @param compName Nombre del componente
     * @param model Texto del componente
     */
    @Override
    public void validate(Problems problems, String compName, String model) {
        if(model.isEmpty()){ //El campo de la edad no debe estar vacío
            String message = NbBundle.getMessage(NombreValidator.class, "MSG_MAY_NOT_BE_EMPTY" , compName);
            problems.add(message);
        }
        for(int i=0; i<model.length(); i++){
            if(!Character.isDigit(model.charAt(i))){ //Todos los caracteres deben ser números enteros
                String message = NbBundle.getMessage(NombreValidator.class, "ERR_NOT_INTEGER" , compName);
                problems.add(message);
            }
        }
    }
    
}
