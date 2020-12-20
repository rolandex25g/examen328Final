using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using Npgsql;

namespace WebApplicationServicioIdentidad
{
    /// <summary>
    /// Descripción breve de WebService1
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // Para permitir que se llame a este servicio web desde un script, usando ASP.NET AJAX, quite la marca de comentario de la línea siguiente. 
    // [System.Web.Script.Services.ScriptService]
    public class WebService1 : System.Web.Services.WebService
    {

        [WebMethod]
        public string HelloWorld()
        {
            return "Hola a todos";
        }
        [WebMethod]
        public string obteneridentidad(String ci)
        {
            String res = "";
            NpgsqlConnection con;
            String cadena = "Server=localhost;Port=5432;User Id=postgres;Password=root;Database=academico2;";
            try
            {
                con = new NpgsqlConnection(cadena);
                con.Open();
                String query = "select nombre,paterno,materno" +
                " from alumno" +
                " where ci='" + ci + "'";
                NpgsqlCommand command = new NpgsqlCommand(query, con);

                NpgsqlDataReader dr = command.ExecuteReader();
                while (dr.Read())
                {
                    res = res + dr[0] + ","+ dr[1] + ","+ dr[2];
                }
                con.Close();
            }
            catch (Exception)
            {
                con = null;

            }
            return res;
        }
    }
}
