package ru.maltsev.api.users.data;

import feign.FeignException;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.maltsev.api.users.domain.AlbumsResponseModel;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "albums-ws", fallbackFactory = AlbumsFallbackFactory.class)
public interface AlbumsServiceClient {

    @GetMapping("/users/{id}/albums")
    List<AlbumsResponseModel> getAlbums(@PathVariable String id);
}

@Component
class AlbumsFallbackFactory implements FallbackFactory<AlbumsServiceClient> {

    @Override
    public AlbumsServiceClient create(Throwable throwable) {
        return new AlbumsServiceClientFallback(throwable);
    }
}


class AlbumsServiceClientFallback implements AlbumsServiceClient {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Throwable throwable;

    public AlbumsServiceClientFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public List<AlbumsResponseModel> getAlbums(String id) {
        if (throwable instanceof FeignException && ((FeignException) throwable).status() == 404) {
            logger.error("404 " + id + " Error message: " + throwable.getLocalizedMessage());
        } else {
            logger.error("Another error " + throwable.getLocalizedMessage());
        }
        return new ArrayList<>();
    }
}