public class Ricetta {

    private int idRicetta, tempoCottura, tempoPreparazione, kcal, idCategoria;
    private String nome, foto, procedimento, email;

    public int getIdRicetta() {
        return idRicetta;
    }

    public void setIdRicetta(int idRicetta) {
        this.idRicetta = idRicetta;
    }

    public int getTempoCottura() {
        return tempoCottura;
    }

    public void setTempoCottura(int tempoCottura) {
        this.tempoCottura = tempoCottura;
    }

    public int getTempoPreparazione() {
        return tempoPreparazione;
    }

    public void setTempoPreparazione(int tempoPreparazione) {
        this.tempoPreparazione = tempoPreparazione;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(String procedimento) {
        this.procedimento = procedimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
