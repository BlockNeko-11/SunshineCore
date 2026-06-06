package io.github.blockneko11.sunshinecore.config.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.minecraft.core.BlockPos;

import java.io.IOException;

public class BlockPosTypeAdapter extends TypeAdapter<BlockPos> {
    @Override
    public void write(JsonWriter out, BlockPos value) throws IOException {
        out.beginObject();
        out.name("x").value(value.getX());
        out.name("y").value(value.getY());
        out.name("z").value(value.getZ());
        out.endObject();
    }

    @Override
    public BlockPos read(JsonReader in) throws IOException {
        in.beginObject();
        int x = in.nextInt();
        int y = in.nextInt();
        int z = in.nextInt();
        in.endObject();

        return new BlockPos(x, y, z);
    }
}
