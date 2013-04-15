package cz.robyer.gamework.scenario.reaction;

import java.io.IOException;

import android.content.res.AssetFileDescriptor;
import android.util.Log;
import cz.robyer.gamework.scenario.Scenario;

/**
 * 
 * @author Robert P�sel
 */
public class SoundReaction extends Reaction {
	protected String value;
	protected int soundId = -1;
	protected float volume = 1.0f;
	protected float pitch = 1.0f;
	
	public SoundReaction(String id, String value) {
		super(id);
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	@Override
	public void setScenario(Scenario scenario) {
		super.setScenario(scenario);
		
		if (soundId == -1) {
			try {
				AssetFileDescriptor descriptor = getContext().getAssets().openFd(value);
				soundId = getScenario().getSoundPool().load(descriptor, 1);
				descriptor.close();
			} catch (IOException e) {
				Log.e(TAG, String.format("Can't load sound '%s'", value));
			}
		}
	}
	
	@Override
	public void action() {
		if (soundId != -1)
			getScenario().getSoundPool().play(soundId, volume, volume, 1, 0, pitch);
	}

}