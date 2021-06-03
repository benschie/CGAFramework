package cga.exercise.game

import cga.exercise.components.geometry.Mesh
import cga.exercise.components.geometry.VertexAttribute
import cga.exercise.components.shader.ShaderProgram
import cga.framework.GLError
import cga.framework.GameWindow
import cga.framework.OBJLoader
import org.lwjgl.opengl.GL11.*

/**
 * Created by Fabian on 16.09.2017.
 */
class Scene(private val window: GameWindow) {
    private val staticShader: ShaderProgram
    private val houseMesh : Mesh // = Mesh(floatArrayOf(), intArrayOf(), arrayOf<VertexAttribute>())
    private val myMesh : Mesh
    private val sphereMesh : Mesh

    //scene setup
    init {
        staticShader = ShaderProgram("assets/shaders/simple_vert.glsl", "assets/shaders/simple_frag.glsl")

        //initial opengl state
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f); GLError.checkThrow()
        glEnable(GL_CULL_FACE); GLError.checkThrow()
        glFrontFace(GL_CCW); GLError.checkThrow()
        glCullFace(GL_BACK); GLError.checkThrow()
        glEnable(GL_DEPTH_TEST); GLError.checkThrow()
        glDepthFunc(GL_LESS); GLError.checkThrow()

        val attribPosition1 : VertexAttribute = VertexAttribute(3, GL_FLOAT, 24, 0)
        val attribColor1 : VertexAttribute = VertexAttribute(3, GL_FLOAT, 24, 12)
        val posAndColorAttributes = arrayOf(attribPosition1, attribColor1)

        val houseVertices : FloatArray = floatArrayOf(
                -0.5f, -0.5f, 0.0f, 0.0f, 0.0f, 1.0f,
                 0.5f, -0.5f, 0.0f, 0.0f, 0.0f, 1.0f,
                 0.5f,  0.5f, 0.0f, 0.0f, 1.0f, 0.0f,
                 0.0f,  1.0f, 0.0f, 1.0f, 0.0f, 0.0f,
                -0.5f,  0.5f, 0.0f, 0.0f, 1.0f, 0.0f
        )

        val houseIndices : IntArray = intArrayOf(
                0, 1, 2,
                0, 2, 4,
                4, 2, 3)

        val myVertices : FloatArray = floatArrayOf(

                -1.0f,  -0.5f, 0.0f, 1.0f, 1.0f, 1.0f, // 0
                -0.75f, -0.5f, 0.0f, 1.0f, 1.0f, 1.0f, // 1
                -1.0f,   0.5f, 0.0f, 1.0f, 1.0f, 1.0f, // 2
                -0.6f,   0.0f, 0.0f, 1.0f, 1.0f, 1.0f, // 3
                -0.5f,   0.0f, 0.0f, 1.0f, 1.0f, 1.0f, // 4
                -0.4f,   0.0f, 0.0f, 1.0f, 1.0f, 1.0f, // 5
                 0.0f,   0.5f, 0.0f, 1.0f, 1.0f, 1.0f, // 6
                -0.25f, -0.5f, 0.0f, 1.0f, 1.0f, 1.0f, // 7
                 0.0f,  -0.5f, 0.0f, 1.0f, 1.0f, 1.0f, // 8
                 0.25f, -0.5f, 0.0f, 1.0f, 1.0f, 1.0f, // 9
                 1.0f,  -0.4f, 0.0f, 1.0f, 1.0f, 1.0f, //10
                 0.25f,  0.0f, 0.0f, 1.0f, 1.0f, 1.0f, //11
                 1.0f,  0.25f, 1.0f, 1.0f, 1.0f, 1.0f, //12
                 0.25f,  0.5f, 0.0f, 1.0f, 1.0f, 1.0f  //13
        )

        val myIndices : IntArray = intArrayOf(
                 0,  1,  2,
                 2,  3,  4,
                 4,  5,  6,
                 6,  7,  8,
                 9, 10, 11,
                11, 12, 13
        )

        houseMesh = Mesh(houseVertices, houseIndices, posAndColorAttributes)
        myMesh = Mesh(myVertices, myIndices, posAndColorAttributes)

        val sphereRes : OBJLoader.OBJResult = OBJLoader.loadOBJ("assets/models/sphere.obj")
        val sphereObjMeshList : MutableList<OBJLoader.OBJMesh> = sphereRes.objects[0].meshes

        val vertexData = sphereObjMeshList[0].vertexData
        val indexData = sphereObjMeshList[0].indexData

        val attribPosition2 : VertexAttribute = VertexAttribute(3, GL_FLOAT, 32, 0)
        val attribTexture2 : VertexAttribute = VertexAttribute(2, GL_FLOAT, 32, 12)
        val attribNormal2 : VertexAttribute = VertexAttribute(3, GL_FLOAT, 32, 20)
        val sphereAttrib = arrayOf(attribPosition2, attribTexture2, attribNormal2)

        sphereMesh = Mesh(vertexData, indexData, sphereAttrib)
    }

    fun render(dt: Float, t: Float) {
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
        staticShader.use()
        //houseMesh.render()
        myMesh.render()
        //sphereMesh.render()
    }

    fun update(dt: Float, t: Float) {}

    fun onKey(key: Int, scancode: Int, action: Int, mode: Int) {}

    fun onMouseMove(xpos: Double, ypos: Double) {}

    fun cleanup() {}
}