package Command;

import java.io.InputStream;

public class Push extends BaseCommand {

    private InputStream is;

    public Push(InputStream is){
        this.is = is;
    }

    @Override
    public void execute() {

    }
}
