package ru.maltsev.api.users.data;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.maltsev.api.users.domain.AlbumsResponseModel;

import java.util.List;

@FeignClient(name = "albums-ws")
public interface AlbumsServiceClient {

    @GetMapping("/users/{id}/albums")
    List<AlbumsResponseModel> getAlbums(@PathVariable String id);
}
