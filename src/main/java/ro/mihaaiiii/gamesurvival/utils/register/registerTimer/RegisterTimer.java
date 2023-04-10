package ro.mihaaiiii.gamesurvival.utils.register.registerTimer;

import ro.mihaaiiii.gamesurvival.GameSurvival;
import ro.mihaaiiii.gamesurvival.utils.register.Registration;

public class RegisterTimer implements Registration {
    private GameSurvival plugin;

    public RegisterTimer(GameSurvival plugin) {
        this.plugin = plugin;
        register(plugin);
    }


    @Override
   public  void register(GameSurvival plugin) {

    }
}
