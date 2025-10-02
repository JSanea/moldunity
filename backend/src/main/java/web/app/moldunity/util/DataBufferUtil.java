package web.app.moldunity.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import reactor.core.publisher.Flux;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DataBufferUtil {
    public static Flux<ByteBuffer> asByteBuffer(Flux<DataBuffer> dataBufferFlux){
        return dataBufferFlux.flatMap(dataBuffer -> {
            List<ByteBuffer> buffers = new ArrayList<>();
            try (var it = dataBuffer.readableByteBuffers()) {
                it.forEachRemaining(buffers::add);
            } catch (Exception e) {
                return Flux.error(new RuntimeException("Failed to read ByteBuffer from DataBuffer", e));
            } finally {
                DataBufferUtils.release(dataBuffer);
            }
            return Flux.fromIterable(buffers);
        });
    }

    public static ByteBuffer asByteBuffer(DataBuffer dataBuffer){
        try (var it = dataBuffer.readableByteBuffers()) {
            if (!it.hasNext()) {
                throw new IllegalStateException("DataBuffer contains no readable ByteBuffer");
            }

            ByteBuffer first = it.next();

            if (it.hasNext()) {
                // If there are multiple ByteBuffers, combine them
                List<ByteBuffer> buffers = new ArrayList<>();
                buffers.add(first);
                it.forEachRemaining(buffers::add);

                int totalSize = buffers.stream().mapToInt(ByteBuffer::remaining).sum();
                ByteBuffer combined = ByteBuffer.allocate(totalSize);

                buffers.forEach(combined::put);
                combined.flip();
                return combined;
            }

            // If only one, return a copy of it
            ByteBuffer copy = ByteBuffer.allocate(first.remaining());
            copy.put(first);
            copy.flip();
            return copy;

        } catch (Exception e) {
            log.error("Failed to extract ByteBuffer from DataBuffer", e);
            throw new RuntimeException("Failed to read buffer", e);
        } finally {
            DataBufferUtils.release(dataBuffer);
        }
    }
}
