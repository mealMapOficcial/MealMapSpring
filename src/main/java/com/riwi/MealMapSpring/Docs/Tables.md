## Endpoint: /tables/available/{id}

**Método:** PUT

**Descripción:** Actualiza el estado de disponibilidad de una mesa.

**Parámetros:**

* **id:** ID de la mesa a actualizar (obligatorio).

**Cuerpo de la petición:**

**Cuerpo de la petición:**
**.Respuesta:**

**Código de estado: 200 (OK) si la actualización es exitosa.
Código de estado: 404 (Not Found) si la mesa no se encuentra**

```json
{
  "available": true // true para disponible, false para no disponible
} 

**Cuerpo de la respuesta:**

{
  "numberTable": 12,
  "available": true
}

