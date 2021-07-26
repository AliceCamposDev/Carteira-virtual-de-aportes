/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import Visao.Main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.aporte;

/**
 *
 * @author Gabriel
 */
public class aporteDAO {

    public static File getFile() {
        File Dados = new File("DadosAportes.txt");
        try {
            if (Dados.createNewFile()) {
                System.out.println("File created: " + Dados.getName() + " at " + Dados.getAbsolutePath());
                FileWriter Writer = new FileWriter(getFile(), true);
                Writer.write("arquivo para armazenar dados da tabela");
                Writer.close();
            } else {
                System.out.println("File selected");

            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao tentar abrir ou criar o arquivo DadosAportes.txt");
        }
        return Dados;
    }

    public static List<aporte> ListarAportes() {  //ler os dados do txt, que vai ser achado ou criado chamando o método getFile()
        try (FileReader marcaLeitura = new FileReader(getFile())) {
            List<aporte> aportes = new ArrayList<>();
            BufferedReader bufLeitura = new BufferedReader(marcaLeitura);
            String linha = bufLeitura.readLine();
            while (linha != null) {
                linha = bufLeitura.readLine();

                try {
                    String DadosSeparados[] = linha.split(","); // separando e convertendo valores (armazenados como string no TXT)
                    double valor = Double.parseDouble(DadosSeparados[1]);
                    Date data = new SimpleDateFormat("dd/MM/yyyy").parse(DadosSeparados[2]);
                    double valorAtual = Double.parseDouble(DadosSeparados[3]);
                    double lucro = Double.parseDouble(DadosSeparados[4]);
                    Double retorno = Double.parseDouble(DadosSeparados[5]);
                    // inserindo os valores na classe e a classe na lista
                    aportes.add(new aporte(DadosSeparados[0], valor, data, valorAtual, lucro, retorno));
                } catch (Exception e) {
                    System.out.println(" - ");//ou erro ao converter data.......
                }
            }
            return aportes;
        } catch (Exception e) {
            System.out.println("Dados procurados nao puderam ser acessados");
            return null;
        }
    }

    
    
    public static void addAporte(aporte a) throws ParseException {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = df.format(a.getData());
            FileWriter Writer = new FileWriter(getFile(), true);
            BigDecimal bdValor = new BigDecimal(a.getValor()).setScale(3, RoundingMode.HALF_EVEN);
            BigDecimal bdRetorno = new BigDecimal(a.getRetorno()).setScale(3, RoundingMode.HALF_EVEN);
            BigDecimal bdAtual = new BigDecimal(a.getValorAtual()).setScale(3, RoundingMode.HALF_EVEN);
            BigDecimal bdLucro = new BigDecimal(a.getLucro()).setScale(3, RoundingMode.HALF_EVEN);
            Writer.write("\r");

            Writer.write(a.getNome() + "," +bdValor + "," + strDate + "," +  bdAtual + "," +bdLucro  + "," +bdRetorno );
            Writer.close();
            System.out.println("Aporte adicionado e salvo");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao tentar editar o arquivo");
        }
    }

    
    
    
    
    
    public static aporte getAporte(int selectedRow) {
        return ListarAportes().get(selectedRow);
    }

    public static void deleteAporte(int TableNum) throws IOException { // cria um rquivo temporario, clona todos os dados do original menos a linha a ser excluida e depois sobrepõe o arquivo original.
        File tempFile = new File("LineDeleter.txt");

        int linhas = 0;

        try (BufferedReader bufLeitura = new BufferedReader(new FileReader(getFile())); BufferedWriter bufEscrita = new BufferedWriter(new FileWriter(tempFile))) {
            
            String linha = bufLeitura.readLine();
             bufEscrita.write(linha);
            while (linha != null) {
                
                linha = bufLeitura.readLine();
                
                if ((linhas != TableNum )&&(! linha.equals("null"))){
                    bufEscrita.write("\r"+linha);
                }
                linhas++;
                
            }
        bufLeitura.close();
        bufEscrita.close();
        }catch (Exception e) {
            System.out.println(" - ");//ou erro ao converter data.......
        }
        
       Files.move(tempFile.toPath(), getFile().toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);


    }

    public static int getlinhas() {
        int linhas = -1;
        try (FileReader marcaLeitura = new FileReader(getFile())) {
            List<aporte> aportes = new ArrayList<>();
            BufferedReader bufLeitura = new BufferedReader(marcaLeitura);
            String linha = bufLeitura.readLine();
            while (linha != null) {
                linha = bufLeitura.readLine();
                linhas++;
            }
        } catch (Exception e) {
            System.out.println("Dados procurados nao puderam ser acessados");
        }
        System.out.println("tem "+linhas);
        return linhas;
    }

    public static void attAporte(String novoValor,int TableNum) throws IOException {
        File tempFile = new File("LineDeleter.txt");
        int linhas = 0;
        try (BufferedReader bufLeitura = new BufferedReader(new FileReader(getFile())); BufferedWriter bufEscrita = new BufferedWriter(new FileWriter(tempFile))) {
            String linha = bufLeitura.readLine();
             bufEscrita.write(linha);
            while (linha != null) {               
                linha = bufLeitura.readLine();
                if ((linhas != TableNum )&&(! linha.equals("null"))){
                    bufEscrita.write("\r"+linha);
                }else{
                    BigDecimal bdNovo = new BigDecimal(novoValor).setScale(3, RoundingMode.HALF_EVEN);
                    String DadosSeparados[] = linha.split(",");
                    bufEscrita.write("\r"+DadosSeparados[0]+","+DadosSeparados[1]+","+DadosSeparados[2]+","+bdNovo+","+DadosSeparados[4]+","+DadosSeparados[5]);
                }
                    
                linhas++;
            }
        bufLeitura.close();
        bufEscrita.close();
        }catch (Exception e) {
            System.out.println(" - ");//ou erro ao converter data.......
        }       
       Files.move(tempFile.toPath(), getFile().toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
    }
}
