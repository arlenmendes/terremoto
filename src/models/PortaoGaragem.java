package models;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import models.Item;
import models.Saida;
import models.Ambiente;



/**
 * Esta classe representa uma Saida do Tipo Portao de Garagem
 * @author arlen
 */
public class PortaoGaragem extends Saida  implements Serializable{
    private Gerador gerador;
    private Paciente paciente;
    public PortaoGaragem(   Ambiente ambiente,
                            String status, 
                            Item token,
                            String descricao,
                            Gerador gerador,
                            Paciente paciente
                        ) {
        super(ambiente, status, token, descricao);
        this.gerador = gerador;
        this.paciente = paciente;
    }
    
    @Override
    public boolean liberarSaida(HashMap<String, Item> itens){
        if(this.gerador.ligado()){
            Item item = itens.get(super.getNomeToken());
                if(item != null){
                    return true;
                }
        }
            
                
        return false;
    }
    
    @Override
    public String getNomeToken(){
        return super.getNomeToken() + " e " + " do gerador ligado";
    }
    
}
