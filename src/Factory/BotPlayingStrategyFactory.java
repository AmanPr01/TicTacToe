package Factory;

import models.BotDifficultyLevel;
import strategies.botplayingstrategies.BotPlayingStrategy;
import strategies.botplayingstrategies.EasyBotPlayingStrategy;
import strategies.botplayingstrategies.HardBotPlayingStrategy;
import strategies.botplayingstrategies.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel difficulty) {
        switch (difficulty) {
            case EASY: return new EasyBotPlayingStrategy();
        }
        switch (difficulty) {
            case MEDIUM: return new MediumBotPlayingStrategy();
        }
        switch (difficulty) {
            case HARD: return new HardBotPlayingStrategy();
        }
        return null;
    }
}
