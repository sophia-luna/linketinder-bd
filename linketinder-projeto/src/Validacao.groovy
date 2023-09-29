class Validacao {

    static int opcaoValida(String input){

        int opcaoMenu
        try{
            opcaoMenu=Integer.parseInt(input)
            if (opcaoMenu>0 && opcaoMenu<=20){
                return opcaoMenu
            }
            else{
                return 0
            }
        }
        catch(NumberFormatException e) {
            return 0
        }
    }

    static boolean idadeValida(String input){

        int idade
        try{
            idade=Integer.parseInt(input)
            if(idade>=18 && idade<100){
                return idade
            }
            else{
                return 0
            }
        }
        catch (NumberFormatException e){
            return 0
        }

    }

    static boolean respostaValida(String input){
        if(input!="s" && input!="n"){
            return false
        }
        return true
    }

}
