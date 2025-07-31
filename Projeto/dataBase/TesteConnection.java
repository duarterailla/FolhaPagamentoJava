package dataBase;

import dataBase.ConnectionFactory;
import util.GravacaoArquivo;
import util.LeituraCSV;

import java.sql.Connection;
import java.util.Scanner;
    public class TesteConnection {

        public static void main(String[] args) {
            Connection conn = new ConnectionFactory().getConnection();
            String diretorio;
            try (Scanner sc = new Scanner(System.in)) {

                System.out.println("Insira o caminho do diretório ou seu arquivo: ");
                diretorio = sc.nextLine();
            }

        }
    }