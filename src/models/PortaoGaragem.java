package models;


import models.Item;
import models.Saida;
import models.Ambiente;



/**
 * Esta classe representa uma Saida do Tipo Portao de Garagem
 * @author arlen
 */
public class PortaoGaragem extends Saida{
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
    public boolean liberarSaida(Item item){
        if(this.gerador.ligado())
            if(item != null)
                if(item == super.status.getToken())
                    return true;
        return false;
    }
    
    @Override
    public String getNomeToken(){
        return super.getNomeToken() + " e " + " do gerador ligado";
    }
    
}
