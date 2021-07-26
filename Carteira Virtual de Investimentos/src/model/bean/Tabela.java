package model.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.dao.aporteDAO;

public class Tabela extends AbstractTableModel {

    private String informacoes[][];
    private int linhas = 0;

    public Tabela() {
        this.linhas = aporteDAO.getlinhas();
        this.informacoes = new String[linhas][6];

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        List<aporte> aportes = aporteDAO.ListarAportes();
        for (int i = 0; i < aportes.size(); i++) {

            this.informacoes[i][0] = aportes.get(i).getNome();
            this.informacoes[i][1] = String.valueOf(aportes.get(i).getValor());
            this.informacoes[i][2] = dateFormat.format(aportes.get(i).getData());
            this.informacoes[i][3] = String.valueOf(aportes.get(i).getValorAtual());
            this.informacoes[i][4] = String.valueOf(aportes.get(i).getLucro());
            this.informacoes[i][5] = String.valueOf(aportes.get(i).getRetorno());

        }

    }

    /*public void attTable(){
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    List<aporte> aportes =  aporteDAO.ListarAportes();
    for(int i = 0 ; i < aportes.size(); i++){
    
    this.informacoes[i][0] = aportes.get(i).getNome();
    this.informacoes[i][1] = String.valueOf(aportes.get(i).getValor());
    this.informacoes[i][2] =  dateFormat.format(aportes.get(i).getData());
    this.informacoes[i][3] = String.valueOf(aportes.get(i).getValorAtual());
    this.informacoes[i][4] = String.valueOf(aportes.get(i).getLucro());
    this.informacoes[i][5] = String.valueOf(aportes.get(i).getRetorno());
    
    }
    }*/
    public void setInformacoes(String[][] informacoes) {
        this.informacoes = informacoes;
    }

    //numero de linhas
    @Override
    public int getRowCount() {
        return this.linhas;
    }

    //numero de colunas
    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        return this.informacoes[linha][coluna];
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return ("Nome");
            case 1:
                return ("V.Aporte");
            case 2:
                return ("Data");
            case 3:
                return ("V.Atual");
            case 4:
                return ("Lucro");
            case 5:
                return ("Retorno");
            default:
                return ("???");

        }

    }

    //Pra apenas a coluna 4 poder ser editada
    /*@Override
    public boolean isCellEditable(int linha, int coluna) {
    if (coluna == 3){
    return true;
    } else{
    return false;
    }
    }*/
    //Para manter o valor que foi editado
    //@Override
    //public void setValueAt(Object novoValor, int linha, int coluna) {
    //this.informacoes[linha][coluna] = (int)novoValor;
    //}
    //Pra definir o tipo do valor que pode ir pros espaços da tabela (ele não ensinou como colocar cada coluna de um tipo)
}
