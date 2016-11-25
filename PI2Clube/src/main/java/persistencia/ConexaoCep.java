package persistencia;

import java.sql.*;

public class ConexaoCep {
    private Connection con;
    private Statement st;
    public ResultSet rs;
    /*private String driver = "org.postgresql.Driver";
    private String url = "jdbc:postgresql://localhost:5432/bd";
    private String login = "postgres";
    private String password = "root";*/
    
    private final String driver = "oracle.jdbc.OracleDriver";
    private final String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private final String user = "AGENDA";
    private final String password = "aluno";
   
    //******************************************************************************//
    //  - Construtor para o objeto da classe Conexao                                //
    //******************************************************************************//
    public ConexaoCep() {      
    }
    
    //******************************************************************************//
    //  - Método para estabelecer uma conexao com o banco de dados, com base nos    //
    //  parametros: driver, url, login e password                                   //
    //******************************************************************************//
    public void conectaBD(){
        try{
         Class.forName(driver);
         con = DriverManager.getConnection(url,user,password);
         st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        }               
        catch(Exception e){
            System.out.println("Falha ao conectar no BD!!");             
        }
    }

    //******************************************************************************//
    //  - Método para encerrar uma conexao com o recordset e a conexao prorpiamente //
    //  dita                                                                        //
    //******************************************************************************//
    public void desconectaBD(){
        try{
            st.close();
            con.close();
        }
        catch(Exception e){
            System.out.println("Falha ao desconectar do BD!!"); 
        }
    }

    //******************************************************************************//
    //  - Método para executar uma instrução SQL no banco de dados                  //
    //******************************************************************************//
    public void executaSQL(String SQL){
        try{
            st.execute(SQL);
        }
        catch(Exception e){
            System.out.println("Falha na execução do SQL!!");            
        }
    }

    //******************************************************************************//
    //  - Método para executar uma instrução SQL no banco de dados, retornando o    //
    //  recordset resultante da instrução SQL                                       //
    //******************************************************************************//
    public ResultSet executaConsulta(String SQL){
        ResultSet rs = null;
        try{
            rs = st.executeQuery(SQL);
            return rs;
        }
        catch(Exception e){
            System.out.println("Falha na consulta!!");           
        }
        return rs;
    }
    
    //******************************************************************************//
    //  - Método para a busca do ultimo registro gerado de qualquer tabela que tenha//
    //  um campo autonumeração, a tabela será identificada no parametro SQL         //
    //******************************************************************************//    
    public int executaConsultaSerial(String SQL){
        int codigo = 0;
        try{
            rs = st.executeQuery(SQL);
            rs.next();
            return rs.getInt("currval");
        }
        catch(Exception e){
            System.out.println("Falha na consulta serial!!");            
        }
        return codigo;
    }
}
