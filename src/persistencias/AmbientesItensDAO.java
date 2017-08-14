/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencias;

import java.io.Serializable;
import models.AmbientesItens;

/**
 *
 * @author arlen
 */
public class AmbientesItensDAO implements Serializable{
    
    private PersistenciaArquivo persistenciaArquivo;
    
    public AmbientesItensDAO() {
        persistenciaArquivo = new PersistenciaArquivo();
    }
    
    public AmbientesItens getAmbientesEItens() {
        AmbientesItens ai = persistenciaArquivo.buscarAmbientes();
        return ai;
    }
}
