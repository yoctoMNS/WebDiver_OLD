package jp.xdomain.html.yoctomns.util;

import jp.xdomain.html.yoctomns.world.World;
import jp.xdomain.html.yoctomns.world.WorldConst;
import jp.xdomain.html.yoctomns.world.WorldData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileUtil {
    public static ArrayList<String> loadTextData(String path) {
        ArrayList<String> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(FileUtil.class.getResourceAsStream(path)))) {
            String line = "";

            while ((line = reader.readLine()) != null) {
                list.add(line);
            }

            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static WorldData buildWorldDataForTextData(World world, String path) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(FileUtil.class.getResourceAsStream(path)))) {
            String line = "";
            String fileName = "";
            int width = 0;
            int height = 0;
            int tileWidth = 0;
            int tileHeight = 0;
            int tileScale = 0;
            StringBuilder mapData = new StringBuilder();
            int idx = 0;

            while ((line = reader.readLine()) != null) {
                if (idx == WorldConst.WORLD_RES_FILE_NAME)      fileName = line;
                else if (idx == WorldConst.WORLD_WIDTH)         width = Integer.parseInt(line);
                else if (idx == WorldConst.WORLD_HEIGHT)        height = Integer.parseInt(line);
                else if (idx == WorldConst.WORLD_TILE_WIDTH)    tileWidth = Integer.parseInt(line);
                else if (idx == WorldConst.WORLD_TILE_HEIGHT)   tileHeight = Integer.parseInt(line);
                else if (idx == WorldConst.WORLD_TILE_SCALE)    tileScale = Integer.parseInt(line);
                else                                            mapData.append(line + "\n");
                idx++;
            }

            return new WorldData(
                    world.getGame(),
                    world.getKeyboard(),
                    fileName,
                    width,
                    height,
                    tileWidth,
                    tileHeight,
                    tileScale,
                    mapData.toString()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
