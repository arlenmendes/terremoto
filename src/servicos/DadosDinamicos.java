/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import java.io.Serializable;
import models.Paciente;

/**
 * Esta classe é responsável por guardar os dados dinamicos e serializa-los.
 * @author arlen
 */
public class DadosDinamicos implements Serializable{
    private int contador;
    private AmbienteServico ambienteServico;
    private Paciente paciente;
    /**
     * Este construtor recebe os dados diamicos a serem serializados.
     * @param as
     * @param p
     * @param contador 
     */
    public DadosDinamicos(AmbienteServico as, Paciente p, int contador) {
        this.ambienteServico = as;
        this.paciente = p;
        this.contador = contador;
    }
    /**
     * Retorna uma instancia  de AmbienteServico.
     * @return 
     */
    public AmbienteServico getAmbienteServico() {
        return this.ambienteServico;
    }
    /**
     * Retorna uma instancia  de Paciente.
     * @return 
     */
    public Paciente getPaciente() {
        return this.paciente;
    }
    /**
     * Retorna o cotador.
     * @return 
     */
    public int getContador() {
        return this.contador;
    }
}
