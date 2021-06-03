package cga.exercise.components.geometry

import org.lwjgl.opengl.GL33.*
import org.lwjgl.opengl.*

/**
 * Creates a Mesh object from vertexdata, intexdata and a given set of vertex attributes
 *
 * @param vertexdata plain float array of vertex data
 * @param indexdata  index data
 * @param attributes vertex attributes contained in vertex data
 * @throws Exception If the creation of the required OpenGL objects fails, an exception is thrown
 *
 * Created by Fabian on 16.09.2017.
 */
class Mesh(vertexdata: FloatArray, indexdata: IntArray, attributes: Array<VertexAttribute>) {
    //private data
    private var vao = 0
    private var vbo = 0
    private var ibo = 0
    private var indexcount = indexdata.size

    init {
        // todo: place your code here
        //cleanup()
        // todo: generate IDs
        vao = glGenVertexArrays()
        vbo = glGenBuffers()
        ibo = glGenBuffers()
        // todo: bind your objects && upload your mesh data && define VertexAttributes
        glBindVertexArray(vao)

        glBindBuffer(GL_ARRAY_BUFFER, vbo) // will be allocated to current vao
        glBufferData(GL_ARRAY_BUFFER, vertexdata, GL_STATIC_DRAW)

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo) // will be allocated to current vao
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexdata, GL_STATIC_DRAW)

        for((i, attrib) in attributes.withIndex()) {
            glEnableVertexAttribArray(i)
            glVertexAttribPointer(i, attrib.n, attrib.type, false, attrib.stride, attrib.offset.toLong())
        }

        // todo: unbind vao, vbo and ibo
        glBindVertexArray(0) //vao
        glBindBuffer(GL_ARRAY_BUFFER, 0) //vbo
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0) //ibo
    }

    /**
     * renders the mesh
     */
    fun render() {
        // todo: place your code here
        glBindVertexArray(vao) //vao binden
        glDrawElements(GL_TRIANGLES, indexcount, GL_UNSIGNED_INT, 0) //Range: 0 to indexcount
        // call the rendering method every frame
    }

    /**
     * Deletes the previously allocated OpenGL objects for this mesh
     */
    fun cleanup() {
        if (ibo != 0) GL15.glDeleteBuffers(ibo)
        if (vbo != 0) GL15.glDeleteBuffers(vbo)
        if (vao != 0) GL30.glDeleteVertexArrays(vao)
    }
}