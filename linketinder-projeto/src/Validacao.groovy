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

    static boolean respostaValida(String input){
        if(input!="s" && input!="n"){
            return false
        }
        return true
    }

}
