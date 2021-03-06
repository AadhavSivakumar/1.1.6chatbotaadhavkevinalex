/**
 * A program to carry on conversations with a human user.
 * This version:
 *
 *      Uses advanced search for keywords 
 *
 *      Will transform statements as well as react to keywords
 *
 * @author Aadhav Sivakumar, Kevin Li, Alex Miller
 * @version December 2018
 *
 */

public class stockbot
{
    /**
     * Get a default greeting   
     * @return a greeting
     */ 
    public String getGreeting()
    {
        return "Hello, let's talk.";
    }
    
    /**
     * Gives a response to a user statement
     * 
     * @param statement
     *            the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement)
    {
        String response = "";
        if (statement.length() == 0)
        {
            response = "Say something, please.";
        }
        else if(statement=="hello"){
            response = "hi!";
        }
        
        
        else if(findKeyword(statement, "I want to invest in", 0)>-1){
            response = transformIWantToInvestInStatement(statement);
        }
        else if(findKeyword(statement, "market cap", 0)>-1){
            response = transformMarketCap(statement);
        }

        // Responses which require transformations
        

        else
        {
            response = getRandomResponse();
        }
        return response;
    }
    
    /**
     * Take a statement with "I want to <something>." and transform it into 
     * "What would it mean to <something>?"
     * @param statement the user statement, assumed to contain "I want to"
     * @return the transformed statement
     */
    private String transformIWantToInvestInStatement(String statement)
    {
        //  Remove the final period, if there is one
        int share_index=0;
        
        String[] company_list = new String[]{"Bank of America","Wells Fargo","Apple","Amazon","Microsoft","Google"};
        String[] prices = new String[]{"24.74","47.66","170.40","1169.00","110.89","1,068.00"};
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword (statement, "I want to invest in", 0);
        
        for(int i=0;i>company_list.length;i++){
            int temp = findKeyword (statement,company_list[i], 0);
            if(temp > 0){
                    share_index=i;
                    
                i = 20;
    
          }
        }
        
        String restOfStatement = prices[share_index];
        return "Right now, one share costs $" + restOfStatement + ".";
    
    
    
    }


    private String transformMarketCap(String statement)
    {
        //  Remove the final period, if there is one
        int mkt_index=0;
        
        String[] company_list = new String[]{"Bank of America","Wells Fargo","Apple","Amazon","Microsoft","Google"};
        String[] mkt_caps = new String[]{"243.24B","226.18B","810.18B","829.38B","850.05B","751.67B"};
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword (statement, "market cap", 0);
        
        for(int i=0;i>company_list.length;i++){
            int temp = findKeyword (statement,company_list[i], 0);
            if(temp > 0){
                    mkt_index=i;
                    
                i = 20;
    
          }
        }
    
        String restOfStatement = mkt_caps[mkt_index];
        return "Its market cap is $" + restOfStatement + ".";
    
       
    }
    
    

    
    
    
    

    
    
    /**
     * Search for one word in phrase.  The search is not case sensitive.
     * This method will check that the given goal is not a substring of a longer string
     * (so, for example, "I know" does not contain "no").  
     * @param statement the string to search
     * @param goal the string to search for
     * @param startPos the character of the string to begin the search at
     * @return the index of the first occurrence of goal in statement or -1 if it's not found
     */
    private int findKeyword(String statement, String goal, int startPos)
    {
        String phrase = statement.trim();
        //  The only change to incorporate the startPos is in the line below
        int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);
        
        //  Refinement--make sure the goal isn't part of a word 
        while (psn >= 0) 
        {
            //  Find the string of length 1 before and after the word
            String before = " ", after = " "; 
            if (psn > 0)
            {
                before = phrase.substring (psn - 1, psn).toLowerCase();
            }
            if (psn + goal.length() < phrase.length())
            {
                after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
            }
            
            //  If before and after aren't letters, we've found the word
            if (((before.compareTo ("a") < 0 ) || (before.compareTo("z") > 0))  //  before is not a letter
                    && ((after.compareTo ("a") < 0 ) || (after.compareTo("z") > 0)))
            {
                return psn;
            }
            
            //  The last position didn't work, so let's find the next, if there is one.
            psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
            
        }
        
        return -1;
    }
    
    /**
     * Search for one word in phrase.  The search is not case sensitive.
     * This method will check that the given goal is not a substring of a longer string
     * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.  
     * @param statement the string to search
     * @param goal the string to search for
     * @return the index of the first occurrence of goal in statement or -1 if it's not found
     */
    private int findKeyword(String statement, String goal)
    {
        return findKeyword (statement, goal, 0);
    }
    


    /**
     * Pick a default response to use if nothing else fits.
     * @return a non-committal string
     */
    private String getRandomResponse()
    {
        final int NUMBER_OF_RESPONSES = 13;
        double r = Math.random();
        int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
        String response = "";
        
        if (whichResponse == 0)
        {
            response = "Interesting, tell me more.";
        }
        else if (whichResponse == 1)
        {
            response = "Hmmm.";
        }
        else if (whichResponse == 2)
        {
            response = "Do you really think so?";
        }
        else if (whichResponse == 3)
        {
            response = "Really?";
        }
        else if (whichResponse == 4)
        {
            response = "Tell me about your family.";
        }
        else if (whichResponse == 5)
        {
            response = "Ask me about stocks.";
        }
        else if (whichResponse == 6)
        {
            response = "I got my major in buisness finance at Stanford.";
        }
        else if (whichResponse == 7)
        {
            response = "Tell me about yourself.";
        }
        else if (whichResponse == 8)
        {
            response = "Are you planning on investing?";
        }
        else if (whichResponse == 9)
        {
            response = "What's your favorite company?";
        }
        else if (whichResponse == 10)
        {
            response = "Tell me about your experience in the market.";
        }
        else if (whichResponse == 11)
        {
            response = "Right now, apple has a market cap of $810.02 billion";
        }
        else if (whichResponse == 12)
        {
            response = "Where did you study?";
        }
        else if (whichResponse == 13)
        {
            response = "You don't say.";
        }

        return response;
    }

}
