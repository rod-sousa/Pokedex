package rodsousa.dev.br.pokedex.model;

public class TypePokemon {

    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    static class Type{
        private String name;

        public String getName() {
            return name;
        }

        public void setBase_stat(String base_stat) {
            this.name = base_stat;
        }
    }
}
