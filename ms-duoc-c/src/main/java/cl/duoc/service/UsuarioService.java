package cl.duoc.service;
import cl.duoc.dto.UsuarioDTO;
import cl.duoc.entity.Usuario;
import cl.duoc.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public List<UsuarioDTO> obtenerUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }
    public UsuarioDTO crearUsuario(UsuarioDTO dto) {
        Usuario usuario = convertirAEntidad(dto);
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return convertirADTO(usuarioGuardado);
    }
    private UsuarioDTO convertirADTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getCorreo(),
                usuario.getTelefono(),
                usuario.getDireccion()
        );
    }
    private Usuario convertirAEntidad(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());
        usuario.setCorreo(dto.getCorreo());
        usuario.setTelefono(dto.getTelefono());
        usuario.setDireccion(dto.getDireccion());
        return usuario;
    }
}