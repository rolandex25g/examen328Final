using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;


using Npgsql;

namespace WebApplicationAspNet
{
    public partial class WebForm1 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            Label6.Text = "";
            TextBox2.Text = "";
            TextBox3.Text = "";
            TextBox4.Text = "";
            TextBox5.Text = "";

            String semestre = TextBox1.Text;
            Dictionary<int, string> dMateria = new Dictionary<int, string>();


            NpgsqlConnection con;
            String cadena = "Server=localhost;Port=5432;User Id=postgres;Password=root;Database=academico2;";
            try
            {
                con = new NpgsqlConnection(cadena);
                con.Open();
                String query = "select idMateria,nombre_materia" +
                " from materia" +
                " where semestre='" + semestre + "'";
                NpgsqlCommand command = new NpgsqlCommand(query, con);

                NpgsqlDataReader dr = command.ExecuteReader();
                while (dr.Read())
                {
                    //res = res + dr[0] + "," + dr[1] + "," + dr[2] + "," + dr[3];
                    dMateria.Add(Convert.ToInt32(dr[0]), Convert.ToString(dr[1]));
                }
                con.Close();
            }
            catch (Exception)
            {
                con = null;
            }
            ListBox1.DataTextField = "Value";
            ListBox1.DataValueField = "Key";
            ListBox1.DataSource = dMateria;
            ListBox1.DataBind();

        }

        protected void ListBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            
        }

        protected void Button3_Click(object sender, EventArgs e)
        {
            String idMateria = ListBox1.SelectedValue.ToString();
            Label6.Text = "";
            NpgsqlConnection con;
            String cadena = "Server=localhost;Port=5432;User Id=postgres;Password=root;Database=academico2;";
            try
            {
                con = new NpgsqlConnection(cadena);
                con.Open();
                String query = "select idMateria,sigla,nombre_materia,semestre" +
                " from materia" +
                " where idMateria='" + idMateria + "'";
                NpgsqlCommand command = new NpgsqlCommand(query, con);

                NpgsqlDataReader dr = command.ExecuteReader();
                while (dr.Read())
                {
                    //res = res + dr[0] + "," + dr[1] + "," + dr[2] + "," + dr[3];

                    TextBox2.Text = Convert.ToString(dr[0]);
                    TextBox3.Text = Convert.ToString(dr[1]);
                    TextBox4.Text = Convert.ToString(dr[2]);
                    TextBox5.Text = Convert.ToString(dr[3]);
                }
                con.Close();
            }
            catch (Exception)
            {
                con = null;
            }
            
        }

        protected void Button2_Click(object sender, EventArgs e)
        {
            String idMateria = TextBox2.Text;
            String sigla = TextBox3.Text;
            String nombre_materia = TextBox4.Text;
            String semestre = TextBox5.Text;
            NpgsqlConnection con;
            String cadena = "Server=localhost;Port=5432;User Id=postgres;Password=root;Database=academico2;";
            if (idMateria.Length > 0)
            {
                try
                {
                    con = new NpgsqlConnection(cadena);
                    con.Open();
                    String query = "update materia set" +
                    " sigla='" + sigla + "', " +
                    " nombre_materia='" + nombre_materia + "', " +
                    " semestre='" + semestre + "'" +
                    " where idMateria='" + idMateria + "'";

                    NpgsqlCommand command = new NpgsqlCommand(query, con);
                    command.ExecuteNonQuery();
                    con.Close();

                   Label6.Text = "Materia actualizada correctamente";
                }
                catch (Exception)
                {
                    con = null;

                }
            }
        }

        protected void Button4_Click(object sender, EventArgs e)
        {
            String idMateria = "";
            String sigla = TextBox6.Text;
            String nombre_materia = TextBox7.Text;
            String semestre = TextBox1.Text;
            NpgsqlConnection con;
            

            String cadena = "Server=localhost;Port=5432;User Id=postgres;Password=root;Database=academico2;";
            if ((sigla.Length > 0) && (nombre_materia.Length > 0) && (semestre.Length > 0))
            {
                try
                {
                    con = new NpgsqlConnection(cadena);
                    con.Open();
                    String querycont = "select max(idMateria) from materia";
                    NpgsqlCommand commandcont = new NpgsqlCommand(querycont, con);
                    NpgsqlDataReader dr = commandcont.ExecuteReader();
                    while (dr.Read())
                    {
                        idMateria = Convert.ToString(Convert.ToInt32(dr[0])+1);
                    }
                    con.Close();

                    Label6.Text = sigla + nombre_materia + semestre+"_"+ idMateria;

                    con.Open();
                    String query = "insert into materia values(" +
                    "'" + idMateria + "', " +
                    "'" + sigla + "', " +
                    "'" + nombre_materia + "', " +
                    "'" + semestre + "')";

                    NpgsqlCommand command = new NpgsqlCommand(query, con);
                    command.ExecuteNonQuery();
                    con.Close();

                    Label6.Text = "Materia creada correctamente";
                }
                catch (Exception)
                {
                    con = null;

                }
            }
        }
    }
}