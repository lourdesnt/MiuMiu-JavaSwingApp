package org.netbeans.validation.api.builtin.stringvalidation;

import org.netbeans.validation.api.Problems;
import org.openide.util.NbBundle;

/**
 * Clase para validar los nombres del formulario
 * @author Lourdes Navarro Tocón
 */
public class NombreValidator extends StringValidator {

    /**
     * Método que permite validar el nombre
     * @param problems Problemas de validación
     * @param compName Nombre del componente
     * @param model Texto del componente
     */
    @Override
    public void validate(Problems problems, String compName, String model) {
        if(model.isEmpty()){ //El campo del nombre no debe estar vacío
            String message = NbBundle.getMessage(NombreValidator.class, "MSG_MAY_NOT_BE_EMPTY" , compName);
            problems.add(message);
        }
    }
    
}
