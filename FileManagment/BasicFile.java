package de.xunifycoding.file;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**To use simple make an object with this as constructor and then it will automatically create and load the file **/

public class BasicFile {

	private File file;
	private FileConfiguration cfg;
	
	public BasicFile(File datafolder, String name) {
		if (!datafolder.exists()) {
			datafolder.mkdir();
		}
		file = new File(datafolder.getPath(), name + ".yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		cfg = YamlConfiguration.loadConfiguration(file);
		cfg.options().copyDefaults(true);
	}
	
	public FileConfiguration getConfig() {
		return cfg;
	}
	
	public File getFile() {
		return file;
	}
	
	public void reload() {
		cfg = YamlConfiguration.loadConfiguration(file);
	}
	
	public void addDefault(String path, Object value) {
		cfg.addDefault(path, value);
		save();
	}
	
	public boolean getBoolean(String path) {
		return cfg.getBoolean(path);
	}
	
	public void set(String path, Object value) {
		cfg.addDefault(path, value);
		save();
	}
	
	public String getString(String path) {
		return cfg.getString(path);
	}
	
	public int getInt(String path) {
		return cfg.getInt(path);
	}
	
	public double getDouble(String path) {
		return cfg.getDouble(path);
	}
	
	public List<String> getStringList(String path) {
		return cfg.getStringList(path);
	}
	
	public void save() {
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
